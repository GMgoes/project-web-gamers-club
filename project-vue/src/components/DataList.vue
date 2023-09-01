<template>
  <div style="margin-top: 10px; color: aliceblue"><h2>Lista de Jogos</h2></div>
  <div style="display: flex; margin-top: 5rem; flex-wrap: wrap; justify-content: space-around">
    <div
      v-for="item in items"
      v-bind:key="item.id"
      style="margin-left: 5px; margin-top: 5px; width: 300px; height: 500px; color: aliceblue"
    >
      <div style="display: flex; justify-content: center; margin-top: 5px">
        <img v-bind:src="item.thumbnail" style="width: 200px; height: 250px; border-radius: 5px" />
      </div>
      <div style="margin-top: 10px">
        <div style="display: flex; justify-content: center">
          <h3 style="height: 5rem">{{ item.name }}</h3>
        </div>
        <div>
          <p>Plataforma: {{ item.console_name }}</p>
          <p>Proprietário: {{ item.owner_name }}</p>
          <p>Contato: {{ transformNumber(item.owner_number) }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    items: Array
  },
  methods: {
    transformNumber(number) {
      let number_temp = number.slice(0, 2)
      return '(' + number_temp + ') ' + number.slice(2)
    }
  }
}
</script>

<style scoped></style>
