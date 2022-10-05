export default {
    template:`
    <nav class="py-2 border-bottom">
        <div class="container d-flex flex-wrap">
        	<ul class="nav">
	            <li class="nav-item">
		           
		           <form class="d-flex">
					<input class="form-control" type="search" placeholder="대구시 중구 남일동" aria-label="Search">
					<button class="btn btn-primary" type="submit" style="margin-right: 10px;"><i class="bi bi-search"></i></button>
					</form>
		           
	            </li>
        	</ul>
	        <ul class="nav me-auto">
	            <li class="nav-item"><a href="#" class="nav-link link-dark px-2 active" aria-current="page">아파트</a></li>
	            <li class="nav-item"><a href="#" class="nav-link link-dark px-2">투·쓰리룸</a></li>
	            <li class="nav-item"><a href="#" class="nav-link link-dark px-2">원룸</a></li>
	        </ul>
        </div>
    </nav>`
}