<template>
  <div >

  </div>
</template>

<script>
import axios from 'axios'
export default {
  data(){
    return{
      name:'',
      score:'',
    }
  },
  mounted(){
    this.init();
  },
  methods:{
    init(){
      let data = {
        'nickname':this.name,
        'beauty':this.score
      }
      axios({
        method:'get',
        url:'http://localhost:8080/api/getlist',
        data:data,
      }).then(res=>{

      }).catch((err)=>{

      })
    }
  }
}
</script>

<style>
.container{

}
</style>
