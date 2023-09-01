<template>
  <div class="search-bar">
    <input
      type="text"
      v-model="query"
      @input="onInput"
      placeholder="Pesquisar..."
      class="search-input"
    />
    <i class="fas fa-search"></i>
  </div>
</template>

<script>
export default {
  data() {
    return {
      query: ''
    }
  },
  methods: {
    onInput() {
      this.$emit('search', this.query)
    }
  }
}
</script>

<style scoped>
.search-bar {
  display: flex;
  align-items: center;
  width: 1110px;
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
  font-weight: bolder;
  color: #fff;
  cursor: pointer;
}
</style>
