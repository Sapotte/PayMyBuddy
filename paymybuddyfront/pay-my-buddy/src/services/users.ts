import type { PostUser, RequestParams } from '../../generated-schemas/paymybuddyback/Api';
import { paymybuddyHttpClient } from "./httpClients";

export const postUser = async (postUser: PostUser, params?: RequestParams): Promise<void> => {
    const { data } = await paymybuddyHttpClient.users.createUser(postUser, {
        ...params,
        format: 'json'
    })
}