<template>
  <div>
    <button @click="toggleTagSelector">Add Tags</button>
    <div v-if="showTagSelector" class="tag-selector">
      <div v-for="tag in availableTags" :key="tag.tagId">
        <input type="checkbox" :value="tag.tagId" v-model="selectedTagIds" /> {{ tag.tagName }}
      </div>
      <input type="text" v-model="customTag" placeholder="Add custom tag" @keyup.enter="addCustomTag" />
      <button @click="confirmTags">Confirm</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, emit } from 'vue';
import axios from 'axios';

const availableTags = ref([]);
const selectedTagIds = ref([]);
const customTag = ref('');
const showTagSelector = ref(false);

const loadTags = () => {
  axios.get('/tag/all')
      .then(response => {
        availableTags.value = response.data.data;
      })
      .catch(err => {
        console.error('Error loading tags:', err);
      });
};

const addCustomTag = () => {
  if (customTag.value.trim() !== '') {
    axios.post('/tag/create', { tagName: customTag.value })
        .then(response => {
          const newTag = response.data.data;
          availableTags.value.push(newTag);
          selectedTagIds.value.push(newTag.tagId);
          customTag.value = '';
        })
        .catch(err => {
          console.error('Error creating custom tag:', err);
        });
  }
};

const toggleTagSelector = () => {
  showTagSelector.value = !showTagSelector.value;
};

const confirmTags = () => {
  showTagSelector.value = false;
  emit('update-tags', selectedTagIds.value);
};

onMounted(() => {
  loadTags();
});
</script>

<style scoped>
.tag-selector {
  margin-bottom: 20px;
}
</style>
