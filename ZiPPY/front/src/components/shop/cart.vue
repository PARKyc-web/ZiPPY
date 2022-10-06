<template>
  <div>
    <v-toolbar flat color="white">
        <v-toolbar-title>장바구니</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn depressed color=#B3E3C3 @click="deleteProduct">
          삭제
        </v-btn>
      </v-toolbar>
      <v-data-table
        v-model="selected"
        :headers="headers"
        :items="products"
        :single-select="singleSelect"
        item-key="shop_product_name"
        show-select
        class="elevation-1"
       >
      </v-data-table>
  </div>
</template>

<script>

  export default {
    data () {
    return {
      singleSelect: false,
      selected: [],
      headers: [
        {
          text: '상품명',
          align: 'start',
          sortable: false,
          value: 'shop_product_name',
        },
        { text: '', value: 'shop_main_img' },
        { text: '옵션', value: 'shop_product_opt' },
        { text: '스토어', value: 'comp_name' },
        { text: '가격', value: 'shop_product_price' },
        { text: '수량', value: 'basket_product_qty'},
        { text: '배송비', value: 'shop_delivery_cost'}
      ],
      products: [
        {
          shop_product_name: '딱딱의자',
          shop_main_img: "<img src='https://blog.kakaocdn.net/dn/c0nsol/btqXrCOZ6J9/XLlGPEHQoIiwwIClQTkVPk/img.png'>",
          shop_product_opt : 'yellow/L',
          comp_name: '예담가구',
          basket_product_qty: '1',
          shop_product_price: '20,000',
          shop_delivery_cost: '3,000',
        },
        {
          shop_product_name: '말랑의자',
          shop_main_img: 'https://blog.kakaocdn.net/dn/c0nsol/btqXrCOZ6J9/XLlGPEHQoIiwwIClQTkVPk/img.png',
          shop_product_opt : 'white/M',
          comp_name: '예담가구',
          basket_product_qty: '2',
          shop_product_price: '10,000',
          shop_delivery_cost: '3,000',
        }
      ],
    }
  },
    methods: {
      deleteProduct(){
        for(var i in this.selected){
          for(var j in this.products) {
            if(this.products[j].shop_product_name == this.selected[i].shop_product_name){
              this.products.splice(j, 1);
            }  
          }
        }
        if(this.selected.length == 0) {
          alert('장바구니에서 삭제할 상품을 선택하셈');
        }
      }
    }
  };
</script>