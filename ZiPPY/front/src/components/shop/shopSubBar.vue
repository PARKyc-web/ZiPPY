<template>
  <div class="pb-1">
    <v-app-bar color="#fff" dense>
      <v-menu left bottom>
        <template v-slot:activator="{ on, attrs }">
          <v-btn icon v-bind="attrs" v-on="on">
            <v-app-bar-nav-icon></v-app-bar-nav-icon>
          </v-btn>
        </template>

        <v-list>
          <v-list-item v-for="n in 5" :key="n" @click="() => {}">
            <v-list-item-title>Category {{ n }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
      <v-spacer></v-spacer>
      <!--검색창-->
      <v-icon>mdi-magnify</v-icon>
      <v-form>
        <v-container>
          <v-row>
            <v-col cols="12" sm="6" md="3">
              <v-text-field></v-text-field>
            </v-col>
          </v-row>
        </v-container>
      </v-form>
      <!-- 검색창 끝 -->
      <!-- 장바구니 -->
      <v-badge
        :value="hover"
        color="#B3E3C3"
        content="10"
        left
        transition="slide-x-transition"
      >
        <v-hover v-model="hover">
          <v-icon color="#212529"> mdi-cart </v-icon>
        </v-hover>
      </v-badge>
      <!-- 장바구니-->
    </v-app-bar>
  </div>
</template>

<script>
export default {
  data: () => ({
    hover: false,
  }),
};
</script>
