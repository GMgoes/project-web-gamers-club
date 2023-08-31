<template>
  <DataList :items="itemsFromAPI" />
  <Foot />
</template>

<script>
import axios from 'axios'
import DataList from './components/DataList.vue'
import Foot from './components/Foot.vue'

export default {
  name: 'App',
  components: {
    DataList,
    Foot
  },
  data() {
    return {
      itemsFromAPI: []
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    async fetchData() {
      try {
        const response = await axios.get('http://localhost:8080/games?size=25')
        this.itemsFromAPI = response.data.content
      } catch (error) {
        console.error('Erro ao buscar dados:', error)
      }
    }
  }
}
</script>
