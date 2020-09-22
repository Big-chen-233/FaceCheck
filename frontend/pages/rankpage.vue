<template>
  <div class="container">
    <div class="rankhead"> <div class="ranktitle"> 排行榜</div> <img  class="img"  src="@/assets/medal.png" alt="图片加载失败"></div>
    <div class="ranklist" v-for="(item,index) in users" :key="index" :v-if="index < 10">
      <div class="everylist">
        <div class="medal1" v-if="index == 0"></div>
        <div class="medal2" v-else-if="index == 1"></div>
        <div class="medal3" v-else-if="index == 2"></div>
        <div class="medal4" v-else>{{item.index}}</div>
        <div class="nickname">{{item.nickname}}</div>
        <div class="rankscore">{{item.beauty}}</div>
      </div>



    </div>

  </div>
</template>

<script>
import axios from 'axios'
export default {
  data(){
    return{
      users:[],
      rankuser:[]
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
        this.users = res.data.map(item=>{
          return{
            ...item,
          }
        })
        this.rankuser = this.users;
        this.cpmpute();
      }).catch((err)=>{
        this.$message.error('请求失败');
      })
    },
    cpmpute(){
      for(let i = 0;i < this.users.length;i++){
        this.users[i].beauty = Math.round(this.users[i].beauty);
      }

      this.users.sort(function(a,b){return b.beauty - a.beauty})

    }
  }
}
</script>

<style>
.container{
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  background:#F5FFFA;
}

.rankhead{
  display: flex;
  margin-top: 7vh;
  width:80vw;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  /* border:solid 1px; */
}

.ranktitle{
  font-size: 1.8rem;
  /* border:solid 1px; */
  text-align: center;
  width: 50vw;
}

.img{
  margin-left: 8vw;
  width: 4rem;
  height: 4rem;
  /* border:solid 1px; */
}

.ranklist{
  width:80vw;
}

.everylist{
  width: 80vw;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  height: 4rem;
}

.medal1{
  width: 4rem;
  height: 4rem;
  background-image: url('@/assets/fitst4.png');
}

.medal2{
  width: 4rem;
  height: 4rem;
  background-image: url('@/assets/second2.png');
}

.medal3{
  width: 4rem;
  height: 4rem;
  background-image: url('@/assets/third3.png');
}

.medal4{
  font-size: 1.5rem;
}

.nickname{
  font-size: 1.5rem;
}

.rankscore{
  font-size: 1.5rem;
}
</style>
