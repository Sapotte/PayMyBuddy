<template>
    <div class="container">
        <div class="card">
            <div class="card-body">
                <h2>Pay My Buddy</h2>
                <form>
                    <div class="mb-3">
                        <label for="email" id="email" class="form-label">Email address</label>
                        <input type="email" class="form-control" placeholder="name@example.com" v-model="email" state="">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Email address</label>
                        <input type="password" class="form-control" placeholder="Create a secured password">
                    </div>
                    <div class="col-12">
                        <button class="btn btn-primary" type="submit" @click="logIn">Submit form</button>
                    </div>
                </form>
                <span><a small @click="goToCreateAnAccount"></a></span>
            </div>
        </div>
    </div>
</template>
<script setup lang="ts">
    import router from '../router';
import { useField, useForm } from 'vee-validate';
    import { useI18n } from 'vue-i18n';
    import { string } from 'yup';

    const { t } = useI18n({
    inheritLocale: true,
    useScope: 'global'
    })

    const { value: email } = useField('email')
    const { value: password } = useField('password')

    const dataSchema = {
        email: string().matches(new RegExp('^[a-zA-Z0-9._-]+@[a-zA-Z0-9_-]+\.[a-zA-Z]{2,3}$'), {
            message: t('error.regex.email'),
            excludeEmptyString: true
        })
    }

    const logIn = () => {
        return null
    }

    const goToCreateAnAccount = async () => {
        await router.push({
            name: 'CreateAccount'
        })
    }

</script>