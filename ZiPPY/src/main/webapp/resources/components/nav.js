let template : `<nav class="navbar navbar-expand-lg navbar-light">
  <div class="container px-4 px-lg-5">
	  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
		     <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
		         <li class="nav-item"><a class="nav-link active" aria-current="page" href="#!">홈</a></li>
		         <!--dropdown-->
		         <li class="nav-item dropdown">
	             <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">카테고리</a>
		             <ul class="dropdown-menu" id="dropdown-nav" aria-labelledby="navbarDropdown">
	                 <li><a class="dropdown-item" href="#!">침대</a></li>
	                 <li><a class="dropdown-item" href="#!">매트리스/토퍼</a></li>
	                 <li><a class="dropdown-item" href="#!">테이블/식탁/책상</a></li>
	                 <li><a class="dropdown-item" href="#!">서랍/수납장</a></li>
	                 <li><a class="dropdown-item" href="#!">책장</a></li>
	                 <li><a class="dropdown-item" href="#!">의자</a></li>
	                 <li><a class="dropdown-item" href="#!">거울</a></li>
	                 <li><a class="dropdown-item" href="#!">조명</a></li>
	                 <li><a class="dropdown-item" href="#!">소품</a></li>
		             </ul>
		         </li>
		     </ul>
		  </div>
	    <!--Search-->
	    <form class="d-flex">
	      <input class="form-control" type="search" placeholder="Search" aria-label="Search">
	      <button class="btn btn-primary" type="submit" style="margin-right: 10px;"><i class="bi bi-search"></i></button>
	    </form>
    </div>
  </nav>`
  
  export default {
    name: `nav`,
    template : template
}