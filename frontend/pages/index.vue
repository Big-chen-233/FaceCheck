<template>
  <div class="container">
      
      <div class="title">靓仔鉴定系统</div>

      <div class="description">很多人去广东就是为了听别人叫自己一句靓仔，
        现在，您不需要千里迢迢地赶往广东，只需在我们鉴定系统上上传您的照片，
        经过数秒的鉴定，就能听到一句亲切的靓仔。</div>
        

      <nuxt-link to="/checkpage"><button class="indexbutton">进入系统</button></nuxt-link> 
      
        
    
    <div class="footer">靓仔小组提供技术支持</div>
  </div>
  
</template>

<script>
import axios from 'axios'
import Qs from 'qs'
export default {}
</script>

<style>
.container {
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
}

.title {
  margin-top: 5vh;
  font-size: 2rem;
}

.description{
  margin-top: 2vh;
  width: 80vw;
  font-size: 1.1rem;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
  overflow: hidden;
}

.indexbutton{
  margin-top: 2vh;
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
