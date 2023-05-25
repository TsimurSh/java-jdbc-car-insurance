<template>
  <b-container class="page animate__animated animate__fadeIn">
    <b-row class="justify-content-center">
      <b-col cols="col-md-6 col-lg-6">

        <table v-if="showTable" class="table table-sm">
          <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Brand</th>
            <th scope="col">Model</th>
            <th scope="col">Insurance Offers:</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <div v-for="(vehicle, index) in vehicles" :key="vehicle.brand">
              <th scope="row">{{ index + 1 }}</th>
              <td>{{ vehicle.brand }}</td>
              <td>{{ vehicle.model }}</td>
              <td v-for="insurance in vehicle.insuranceOffers" :key="insurance.id">
                {{ "Insurer: " + insurance.insurer }} {{ "Price: " + insurance.price }}
              </td>
            </div>
          </tr>
          </tbody>
        </table>


        <b-container v-if="!showTable">
          <b-input class = "mb-1" v-model="userPrincipal.login" type="text" placeholder="Login" required/>
          <b-input v-model="userPrincipal.password" type="text" placeholder="Password" required/>
          <b-button
              @click="sendLoginPassword"
              class="container-fluid mt-4 b btn-success btn-lg btn-block animate__animated animate__fadeInUp animate__delay-0s"
          >
            Find All Vehicles & Insurance Offers
          </b-button>
        </b-container>
      </b-col>
    </b-row>
    <b-col class="mt-3">
      <b-alert v-if="showErrorAlert" show variant="danger">
        Something went wrong, please try again.
      </b-alert>
    </b-col>
  </b-container>
</template>
<script lang="ts">
import Vue from 'vue'
import {Vehicle} from '~/api/model/Vehicle'
import Cookies from 'js-cookie'


export default Vue.extend({
  data() {
    return {
      showTable: false,
      showErrorAlert: false,
      userPrincipal:
          {
            login: 'Leo2',
            password: 'test'
          },
      vehicles: new Array<Vehicle>()
    }
  },
  methods: {
    async sendLoginPassword() {
      const token = (await this.$axios.post('/login', this.userPrincipal)).data.body
      console.log(`response of secret token: ${token}`)

      Cookies.set('token', token)
      this.$axios.setHeader('Authorization', token)

      this.vehicles = (await this.$axios.get('/api/vehicle', this.userPrincipal)).data
      this.showTable = true;

      console.log(this.vehicles)
    }
  }
})
</script>

<style scoped>
form {
  margin-top: 100px;
  text-align: center;
}

input:focus {
  color: darkblue;
}
</style>
