<template>
    <div class="container">
        <div class="card">
            <h1>{{ $t('commons.createAccount.title') }}</h1>
            <form @submit="createAccount">
                <div class="mb-3">
                    <label for="firstName" class="form-label">{{ $t('commons.form.field.firstName') }}</label>
                    <input type="text" name="firstName" id="firstName" v-model="firstName">
                </div>
                <div class="mb-3">
                    <label for="lastName" class="form-label">{{ $t('commons.form.field.lastName') }}</label>
                    <input type="text" name="lastName" id="lastName" v-model="lastName">
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">{{ $t('commons.form.field.email') }}</label>
                    <input type="email" name="email" id="firstName" v-model="email">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">{{ $t('commons.form.field.password') }}</label>
                    <input type="password" name="password" id="firstName" v-model="password">
                </div>
                <div class="col-12">
                    <button class="btn btn-primary" type="submit">{{ $t('commons.createAccount.validate') }}</button>
                </div>
            </form>
        </div>
    </div>
</template>
<script setup lang="ts">
    import type { PostUserFormData } from '../form/schema';
import { useUsersStore } from '../stores/users';
import type { PostUser } from '../../generated-schemas/paymybuddyback/Api';
    import { useField, useForm } from 'vee-validate';
import { handleError } from 'vue';
    import { useI18n } from 'vue-i18n';
    import { string } from 'yup';

    const userStore = useUsersStore()

    const { t } = useI18n({
        inheritLocale: true,
        useScope: 'global'
    })

    const { value: firstName } = useField('firstname')
    const { value: lastName } = useField('lastName')
    const { value: email } = useField('email')
    const { value: password } = useField('password')

    const dataSchema = {
        lastName: string().matches(new RegExp('[a-zA-Z-]'), {
            message: t('errors.regex.name'),
            excludeEmptyString: true
        }),
        firstName: string().matches(new RegExp('[a-zA-Z-]'), {
            message: t('errors.regex.name'),
            excludeEmptyString: true
        }),
        email: string().matches(new RegExp('^[a-zA-Z0-9._-]+@[a-zA-Z0-9_-]+\.[a-zA-Z]{2,3}$'), {
            message: t('error.regex.email'),
            excludeEmptyString: true
        }),
        password: string().matches(new RegExp('^.{8,}$'), {
            message: t('error.regex.password'),
            excludeEmptyString: true
        })
    }

    const {handleSubmit, errors} = useForm<PostUserFormData>({validationSchema: dataSchema})

    const createAccount = handleSubmit(
        async (result) => {
            const postUser = {
            email: result.email,
            password: result.password,
            name: result.lastName,
            firstName: result.firstName,
            } as PostUser

            try {
                await userStore.postUserAction(postUser)
            } catch (error) {
            }
        }
    )
</script>