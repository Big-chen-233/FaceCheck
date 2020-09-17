<template>
<div class="container">

  <div class="hidedescription" ref="myNotice">很多人去广东就是为了听别人叫自己一句靓仔，
        现在，您不需要千里迢迢地赶往广东，只需在我们鉴定系统上上传您的照片，
        经过数秒的鉴定，就能听到一句亲切的靓仔。</div>  
  <div class="bg"  @click="back" ref="myBg"></div>
  <div class="box" ref="myBox">
  
      <div class="title">靓仔鉴定系统</div>

      <div class="coverdescription" @click="comeout">
      <div style="text-align:center;font-size:1.4rem" >简介</div>
      <div class="description">很多人去广东就是为了听别人叫自己一句靓仔，
        现在，您不需要千里迢迢地赶往广东，只需在我们鉴定系统上上传您的照片，
        经过数秒的鉴定，就能听到一句亲切的靓仔。
      </div>
        
      </div>
      

      <nuxt-link to="/checkpage" style="margin-top: 5vh;"><button class="indexbutton">进入系统</button></nuxt-link> 
      
        
    
    <div class="footer">靓仔小组提供技术支持</div>
  </div>
</div>  
</template>

<script>
import axios from 'axios'
import Qs from 'qs'
export default {
  data(){
   
    return{
      judge:false,
    }
  },
  mounted(){

  },
  methods:{
    
    comeout(){
      
      if(this.judge == false){
        this.$refs.myNotice.style = 'opacity:1.0;z-index:2000';
        this.$refs.myBox.style = 'filter:blur(3px)';
        this.judge = true;
        this.$refs.myBg.style.zIndex = 2;
       
      }
    },

    back(){
      
      if(this.judge == true){
        this.$refs.myNotice.style = 'opacity:0;z-index:0';
        this.$refs.myBox.style = 'filter:blur(0px)';
        this.judge = false;
        this.$refs.myBg.style.zIndex = -1;
        
      }
    },
    
  }
}
</script>

<style>
.container {
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  /* background-color:#F5F5F5; */
}

.bg{
  width: 100vw;
  height: 100vh;
  filter: blur(5px);
  position:absolute;
  z-index: -1;
}

.box{
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  /* background-color:#F5F5F5; */
  position: relative; 

}

.title {
  margin-top: 5vh;
  font-size: 1.8rem;
}

.coverdescription{
  margin-top: 2vh;
  border:dashed 3px lightgray;
  width: 80vw;
  padding: 1.5vh 5vw 3vh 5vw;
  border-radius: 10px;
}

.description{
  margin-top: 0.3vh;
  width: 70vw;
  font-size: 1.1rem;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
  overflow: hidden;
}

.hidedescription{
  margin-top: 25vh;
  opacity:0;
  transition:opacity 0.5s;
  width: 90vw;
  background-color:white;
  color: black;
  text-align: center;
  border-radius: 10px;
  border:0;
  box-shadow: 0 2px 12px 0 rgba(0,0,0,.2);
  position:absolute;
  padding: 20px;
  font-size: 1.2rem;
}


.notice{
  border-radius: 5px;
  width:80vw;
}

.indexbutton{
  width: 70vw;
  max-width: 19rem;
  min-width: 15rem;
  height:3.2rem;
  background-color: #5E8CF7;
  border-radius: 10px;
  outline: 0;
  font-size: 20px;
  line-height: 28px;
  color:white;
  letter-spacing:5px;
  border:0;
}



.footer{
  position: absolute;
  bottom: 2rem;
  justify-self: end;
  font-size: 1rem;
  line-height: 22px;
  color: #A6A6A6;
  text-align: center;
}
</style>
