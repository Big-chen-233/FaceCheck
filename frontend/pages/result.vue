<template>
<div class="container" ref="box">

  <div class="fstbox">
      <div class="row">
        <img class="img"
        :src="img"
        ref="img">
        <div style="width:7vw"></div>
        <div>
          <div class="retresult">{{name}}</div>
          <div class="retresult">你的颜值得分是:</div>
        </div>
      </div>

      <div class="retscore">~&nbsp;&nbsp;<i>{{score}}</i>&nbsp;&nbsp;~</div>
      <div class="retevaluate">{{evaluate}}</div>
  </div>


  <div class="rank">Particulir Card</div>
  <div class="secbox">
    <div class="chara1">{{eye_status}}</div>
    <div class="chara2" >{{expression}}</div>
    <div class="chara">{{emotion}}</div>
    <div class="chara">#{{suggest}}</div>




  </div>






</div>
</template>

<script>
import axios from 'axios'
export default {
    data(){
      return{
        name:'你未输入昵称',
        score:'0',
        img:'',
        evaluate:'恭喜你，你是一个名副其实的靓仔！',

        expression:'',
        emotion:'',
        eye_status:'',
        gender:'',
        user:[],
        suggest:'',
        imgFile:'',
      }
    },
    mounted(){
      this.itit();
      this.change();
    },
    methods:{
      itit(){
        let windowH = window.screen.height;
        this.$refs.box.style.height = windowH + 'px';
        // console.log(windowH);

        this.name = localStorage.getItem('name');
        // console.log(this.name);
        if(this.name == '' || this.name == null)
          this.name = '你未输入昵称'
        this.score = parseInt(localStorage.getItem('score'));
        this.score = Math.round(this.score);


        this.img = localStorage.getItem('mypic');
        console.log(this.img);

        if(this.score >= 60){
          this.evaluate = '恭喜你，你是一只名副其实的靓仔!';}
        else{
          this.evaluate = '差一点点就可以成为靓仔，加油!'}

        this.gender = localStorage.getItem('gender');

        this.eye_status = localStorage.getItem('eye_status');
          if(this.eye_status == 1){
            if(this.gender == 'male')
              this.eye_status = '你的双眼炯炯有神，仿佛蕴含星空';
            else
              this.eye_status = '你的眸中点点星光，宛若星星有泪';
          }
          else{
            if(this.gender == 'male')
              this.eye_status = '你可以试着睁开双眼，去感受这世界';
            else
              this.eye_status = '你可以试着睁开双眼，去感受这世界';
          }

        this.expression = localStorage.getItem('expression');
          if(this.expression == 'none'){
            this.expression = '你面无表情';
            this.emotion = '看起来很冷漠';
            this.suggest = '有些时候，冷漠的心需要温暖的微笑。';
          }
          else if(this.expression == 'smile'){
            this.expression = '你面带微笑';
            this.emotion = '看起来很开心';
            this.suggest = '微笑向暖，安之若素；你若盛开，清风自来。';
          }
          else{
            his.expression = '你面带悲伤';
            this.emotion = '看起来很难过';
            this.suggest = '再黑的黑夜也会迎来黎明，再长的坎坷也会出现路平。';
          }

      },

      base64ImgtoFile(dataurl, filename = 'file') {
        let arr = dataurl.split(',')
        let mime = arr[0].match(/:(.*?);/)[1]
        let suffix = mime.split('/')[1]
        let bstr = atob(arr[1])
        let n = bstr.length
        let u8arr = new Uint8Array(n)
        while (n--) {
          u8arr[n] = bstr.charCodeAt(n)
        }
        return new File([u8arr], `${filename}.${suffix}`, {
          type: mime
        })
      },

      change(){
        this.imgFile = this.base64ImgtoFile(this.img);
        this.$refs.img.style = `backgroundPosition: center;backgroundSize: cover;backgroundRepeat: no-repeat;backgroundImage: url(${this.imgFile});`
        console.log(this.imgFile);
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
  /* background: rgb(0, 187, 255); */
  background: #F5FFFA;
  /* background:#00BFFF; */
}

.fstbox{
  margin-top: 5vh;
  width: 92vw;
  /* border:solid 1px; */
  background: #68e8ff;
  padding: 0 0 3.5vh 0;
  /* border-radius: 5px; */
  box-shadow: 2px 0px 5px #68e8ff,
              0px -2px 5px #68e8ff,
              -2px 0px 5px #68e8ff,
              0px 2px 5px #68e8ff;
}

.row{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content:center;
  padding:2vh 0vh 0vh 0vh;
  /* border:solid 1px; */
}

.retresult{
  color: black;
  text-align: center;
  font-size: 1.2rem;
}

.retscore{
  margin-top: -1vh;
  text-align: center;
  font-size: 4rem;
  color:#FF0033;
  /* border:solid 1px; */
  line-height: 4.5rem;
}

.retevaluate{
  color: #FF0033;
  text-align: center;
  font-size: 1.2rem;
}

.rank{
  width: 50vw;
  max-width: 15rem;
  min-width: 2rem;
  background: #FFCC00;
  height: 2.7rem;
  border-radius: 25px;
  margin-top: -1.1rem;
  box-shadow: 2px 0px 2px #FFCC00,
              0px -2px 2px #FFCC00,
              -2px 0px 2px #FFCC00,
              0px 2px 2px #FFCC00;
  text-align: center;
  line-height: 2.7rem;
  font-size: 1.4rem;
  color: aliceblue;
}

.secbox{
  width: 80vw;
  margin-top: 3vh;
  /* border:solid 1px; */
  /* padding-top: 1rem; */
  text-align: center;
}

.img{
  border-radius: 50%;
  width: 5rem;
  height:auto;
  background-color: white;
}

.chara{
  margin-top: 1vh;
  font-size: 1.2rem;
  line-height: 2rem;
}

.chara2{
  margin-top: 1vh;
  font-size: 1.2rem;
  line-height: 2rem;
}

.chara1{
  margin-top: 1vh;
  font-size: 1.2rem;
  line-height: 2rem;
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
@media screen and (max-width: 300px){
  .retevaluate{
    font-size: 1rem;
  }
  .retscore{
    font-size: 3.2rem;
  }
}
</style>
