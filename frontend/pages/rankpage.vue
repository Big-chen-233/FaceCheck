<template>
  <div class="container">
    <div class="rankhead"> <div class="ranktitle"> 排行榜</div> <img  class="medalimg"  src="@/assets/medal.png" alt="图片加载失败"></div>

    <div class="with_lineTitle">
　　  <span class="line"></span>
　　  <span class="Tbox">你和靓仔的差距有多少?</span>
　　  <span class="line"></span>
    </div>

    <div class="ranklist">
      <div class="everylist" v-for="(item,index) in users" :key="index" :v-if="index < 10">
        <div class="medal1" v-if="index == 0"></div>
        <div class="medal2" v-else-if="index == 1"></div>
        <div class="medal3" v-else-if="index == 2"></div>
        <div class="medal4" v-else>{{index + 1}}</div>
        <div class="nickname">{{item.nickname}}</div>
        <div class="rankscore">{{item.beauty}}</div>
        <div class="star"></div>
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
  margin-top: 5vh;
  width:80vw;
  flex-direction: row;
  height: 18vh;
  justify-content: flex-start;
  align-items: center;
  /* border:solid 1px; */
  color: #646464;
  /* border: solid 3.5px rgb(84, 185, 253); */
  border-radius: 10px;
  background: rgb(200, 244, 247);
  box-shadow: 6px 6px 6px  rgb(129, 201, 248);
}

.ranktitle{
  font-size: 1.8rem;
  /* border:solid 1px; */
  text-align: center;
  width: 45vw;
}

.with_lineTitle{
  margin-top: 5vh;
  display: flex;
  align-items: center;
  justify-content: center;
}

.with_lineTitle .line{
  height: 2.5px;
  background-color:rgb(16, 219, 255);
  width: 17vw;
}

.with_lineTitle .Tbox{
  padding: 0px 10px;
  width: 52vw;
  text-align: center;
  font-size: 1rem;
}

.medalimg{
  margin-left: 8vw;
  width: 4rem;
  height: 4rem;
  /* border:solid 1px; */
}

.ranklist{
  padding: 5px 5px 5px 5px;
  /* border:solid 1px; */
  margin-top: 3vh;
  width:86vw;
  /* background: white; */
  /* border-radius: 10px; */
}

.everylist{
  margin-top: 1vh;
  width: 83vw;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  height: 3.3rem;
  background: white;
  border-radius: 10px;
  border:solid 1px gray;
}

.medal1{
  margin-left: 2vw;
  width: 3rem;
  height: 3rem;
  background-image: url('../assets/fitst4.png');
  background-position: center;
  background-size: cover;
  background-repeat: no-repeat;
  /* border: solid 1px; */
}

.medal2{
  margin-left: 2vw;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 3rem;
  height: 3rem;
  background-image: url('../assets/second2.png');
  background-position: center;
  background-size: cover;
  background-repeat: no-repeat;
  /* border: solid 1px; */
}

.medal3{
  margin-left: 2vw;
  width: 3rem;
  height: 3rem;
  background-image: url('../assets/third3.png');
  background-position: center;
  background-size: cover;
  background-repeat: no-repeat;
  /* border: solid 1px; */
}

.medal4{
  margin-left: 2vw;
  font-size: 1.5rem;
  /* border: solid 1px; */
  width: 3rem;
  text-align: center;
  color: rgb(14, 170, 245);
}

.nickname{
  margin-left: 3vw;
  font-size: 1.2rem;
  /* border: solid 1px; */
  width: 40vw;
}

.rankscore{
  margin-left: 5vw;
  font-size: 1.5rem;
  /* border: solid 1px; */
  color:rgb(245, 207, 65)
}

.star{
  margin-left: 1vw;
  width: 2.2rem;
  height: 2.2rem;
  background-image: url('../assets/star.png');
  background-position: center;
  background-size: cover;
  background-repeat: no-repeat;
  /* border:solid 1px; */
}

@media screen and (max-width: 300px){
  .nickname{
    font-size: 1rem;
  }
  .with_lineTitle .line{
    width:10vw;
  }
  .with_lineTitle .Tbox{
    width:70vw;
  }
}

@media screen and (max-width: 320px) and (min-width: 300px){
  .with_lineTitle .Tbox{
    width:60vw;
  }
}
</style>
