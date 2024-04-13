import type { PostUser } from "../../generated-schemas/paymybuddyback/Api";
import { defineStore } from "pinia";
import { postUser } from "../services/users";

export const useUsersStore = defineStore('users', () => {

    async function postUserAction(postUserDto: PostUser): Promise<void> {
        await postUser(postUserDto)
    }

    return {
        postUserAction
    }
})