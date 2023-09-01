<template>
  <div class="search-bar">
    <select id="option_plataform" @click.prevent="onClick" name="select">
      <option value="TODOS" select>Todos</option>
      <option value="PC">Computador</option>
      <option value="PS5">PS5</option>
      <option value="PS3">PS3</option>
      <option value="PS4">PS4</option>
      <option value="XBOX">XBOX</option>
      <option value="XBOXONE">XBOX ONE</option>
    </select>
    <input
      type="text"
      v-model="name_game_query"
      @input="onInput"
      placeholder="GTA V, FIFA 22..."
      class="search-input"
    />
    <i class="fas fa-search">Pesquisar</i>
  </div>
</template>

<script>
export default {
  data() {
    return {
      name_game_query: ''
    }
  },
  methods: {
    onInput() {
      this.$emit('search', this.name_game_query, document.getElementById('option_plataform').value)
    },
    onClick() {
      this.$emit('click', this.name_game_query, document.getElementById('option_plataform').value)
    }
  }
}
</script>

<style scoped>
.search-bar {
  display: flex;
  align-items: center;
  width: 1110px;
  height: 50px;
  padding: 5px;
  background-color: #16274e;
  border-radius: 5px;
}

.search-input {
  border: none;
  outline: none;
  font-weight: bolder;
  flex: 1;
  padding: 5px;
  background: transparent !important;
  color: #fff;
}

.search-bar i {
  margin-left: 10px;
  margin-right: 15px;
  font-weight: bolder;
  color: #fff;
  cursor: pointer;
}

select {
  background-color: #16274e;
  color: white;
  border: none;
  padding: 10px;
  border-radius: 5px;
  outline: none;
}

select option {
  background-color: #16274e;
  color: white;
  font-size: 15px;
}

select option:hover {
  background-color: #2a3e75;
}

select::-ms-expand {
  display: none;
}
</style>
