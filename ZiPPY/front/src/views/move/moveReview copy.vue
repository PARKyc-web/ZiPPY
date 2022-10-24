<template>

  <div class="review-wrap">

    <move-nav-bar @click="categoryVal=$event.target.innerText"></move-nav-bar>

    <v-row justify="center">
    <v-expansion-panels accordion>
      <v-expansion-panel
        v-for="(item,i) in 5"
        :key="i"
      >
        <v-expansion-panel-header>Item</v-expansion-panel-header>
        <v-expansion-panel-content>
          <v-card>
    <v-toolbar
      flat
      color="success lighten-2"
      depressed
    >
      <v-toolbar-title>Submit a post</v-toolbar-title>
    </v-toolbar>

    <v-card-text>
      <v-text-field
        filled
        label="Title"
        value="My new post"
      ></v-text-field>

      <v-textarea
        filled
        label="Text"
        value="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse"
      ></v-textarea>
      </v-card-text>
      <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
        color="success lighten-1"
        depressed
      >
        수정
      </v-btn>
      <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
        color="grey lighten-1"
        depressed
      >
        삭제
      </v-btn>
    </v-card-actions>
    </v-card-actions>  
    </v-card>
     
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </v-row>

  </div>
</template>

<script>
import MoveNavBar from '../../components/move/MoveNavBar.vue';

export default {
  components: {
    MoveNavBar
  }
}
</script>

<style scoped>

</style>