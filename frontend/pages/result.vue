<template>
<div class="container" ref="box">

  <div class="fstbox">
      <div class="row">
        <div class="img"></div>
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
        {{expression}}
        {{emotion}}
        {{face_shape}}
        {{glasses}}
        {{gender}}

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
        glasses:'',
        gender:'',
        user:[],
      }
    },
    mounted(){
      this.itit();
    },
    methods:{
      itit(){
        let windowH = window.screen.height;
        this.$refs.box.style.height = windowH + 'px';
        console.log(windowH);

        this.name = localStorage.getItem('name');
        if(this.name == '' || this.name == null)
          this.name = '你未输入昵称'
        this.score = localStorage.getItem('score');


        this.img = localStorage.getItem('mypic');
        if(this.score >= 60){
          this.evaluate = '恭喜你，你是一只名副其实的靓仔!';}
        else{
          this.evaluate = '差一点点就可以成为靓仔，加油!'}

        this.expression = localStorage.getItem('expression');
          this.expression = '你充满了' + this.expression;

        this.emotion = localStorage.getItem('emotion');
          this.emotion = '你看起来很' + this.emotion;

        this.gender = localStorage.getItem('gender');

        this.glasses = localStorage.getItem('glasses');
          this.glasses = (this.glasses == false)?('你没戴眼镜，看起来视力保护得很好'):(this.gender == true)?('你的眼镜真帅气!'):('你的眼镜真漂亮!');




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
  border:solid 1px;
  margin-top: -1.5rem;
  padding-top: 1.5rem;
  text-align: center;
}

.img{
  border-radius: 50%;
  width: 5rem;
  height:5rem;
  background-color: white;
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
