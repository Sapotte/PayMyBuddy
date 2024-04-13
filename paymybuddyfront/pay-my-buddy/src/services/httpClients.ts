import { Api as paymybuddyapi } from "../../generated-schemas/paymybuddyback/Api"

export const paymybuddyHttpClient = new paymybuddyapi({
    baseUrl: import.meta.env.BASE_URL || ''
})