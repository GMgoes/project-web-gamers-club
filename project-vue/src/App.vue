<template>
  <search-bar @search="fetchResults" @click="fetchResults"></search-bar>
  <DataList :items="itemsFromAPI" />
  <Foot />
</template>

<script>
import axios from 'axios'
import DataList from './components/DataList.vue'
import Foot from './components/Foot.vue'
import SearchBar from './components/SearchBar.vue'

export default {
  name: 'App',
  components: {
    DataList,
    Foot,
    SearchBar
  },
  data() {
    return {
      itemsFromAPI: []
    }
  },
  created() {
    this.fetchAll()
  },
  methods: {
    async fetchAll() {
      try {
        const response = await axios.get('http://localhost:8080/games')
        this.itemsFromAPI = response.data.content
      } catch (error) {
        console.error('Erro ao buscar dados todos os dados de jogos', error.message)
      }
    },
    async fetchResults(query = '', option_plataform) {
      console.log(query)
      console.log(option_plataform)
      if (query.length > 0) {
        if (option_plataform != 'TODOS') {
          try {
            const response = await axios.get(
              `http://localhost:8080/games/search?game_name=${query}&console_name=${option_plataform}`
            )
            this.itemsFromAPI = response.data.content
          } catch (error) {
            console.error('Erro ao buscar dados - Params: option_plataform, query', error.message)
          }
        } else {
          try {
            const response = await axios.get(
              `http://localhost:8080/games/search?game_name=${query}`
            )
            this.itemsFromAPI = response.data.content
          } catch (error) {
            console.error('Erro ao buscar dados - Params: query', error.message)
          }
        }
      } else {
        if (option_plataform != 'TODOS') {
          try {
            const response = await axios.get(
              `http://localhost:8080/games/search?console_name=${option_plataform}`
            )
            this.itemsFromAPI = response.data.content
          } catch (error) {
            console.error('Erro ao buscar dados - Params: option_plataform', error.message)
          }
        } else {
          await this.fetchAll()
        }
      }
    }
  }
}
</script>
