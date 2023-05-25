import {NuxtConfig} from '~/node_modules/@nuxt/types'

const config: NuxtConfig = {
    ssr: true,
    head: {
        title: 'Timur',
        meta: [
            {charset: 'utf-8'},
            {name: 'viewport', content: 'width=device-width, initial-scale=1'},
            {hid: 'description', name: 'description', content: 'Tsimur️'}
        ],
        link: [
            {rel: 'icon', type: 'image/x-icon', href: '/favicon.ico'}
        ]
    },
    publicRuntimeConfig: {API_URL: 'http://localhost:8080'},
    axios: {
        baseURL: 'http://localhost:8080',
    },
    components: true,
    css: ['@/assets/css/style', '@/assets/fonts/fonts', 'animate.css/animate.min.css'],
    modules: ['bootstrap-vue/nuxt', '@nuxtjs/axios'],
    buildModules: ['@nuxt/postcss8', '@nuxt/typescript-build', '@nuxtjs/stylelint-module']
}

export default config
