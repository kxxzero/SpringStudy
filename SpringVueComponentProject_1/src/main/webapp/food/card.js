const foodCard={
	props:['food_list'],
	template:`<div class="col-md-3" v-for="vo in $parent.food_list">
	    <div class="thumbnail">
	      <a href="#">
	        <img :src="'https://www.menupan.com'+vo.poster" style="width:100%">
	        <div class="caption">
	          <p>{{vo.name}}</p>
	        </div>
	      </a>
	    </div>
	</div>`
}