<template>
  <nav class="py-2 border-bottom">
    <div class="container d-flex flex-wrap">
      <ul class="nav me-auto">
        <li class="nav-item">
          <a href="#" class="nav-link link-dark px-2 active" aria-current="page"
            >견적받기</a
          >
        </li>
        <li class="nav-item">
          <a href="#" class="nav-link link-dark px-2">업체조회</a>
        </li>
        <li class="nav-item">
          <a href="#" class="nav-link link-dark px-2">업체평가하기</a>
        </li>
        <li class="nav-item">
          <a href="#" class="nav-link link-dark px-2">업체후기</a>
        </li>
        <li class="nav-item">
          <a href="#" class="nav-link link-dark px-2">커뮤니티</a>
        </li>
        <li class="nav-item">
          <a href="#" class="nav-link link-dark px-2">마이페이지</a>
        </li>
      </ul>
      <ul class="nav">
        <li class="nav-item">
          <a href="#" class="nav-link link-dark px-2">Login</a>
        </li>
        <li class="nav-item">
          <a href="#" class="nav-link link-dark px-2">Sign up</a>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
export default {};
</script>

<style scoped>
@charset "UTF-8";
header {
  margin-bottom: 0px;
  padding-bottom: 0px;
  background-color: #b3e3c3;
  color: white;
}

.header-icon {
  margin-right: 30px;
  color: black;
}

nav {
  background-color: white;
}

/* === START memberSignUp.jsp CSS */
.member-body {
  background: white;
}

.member-body button {
  color: black;
  background-color: #b3e3c3;
}

.btn-login {
  font-size: 0.9rem;
  letter-spacing: 0.05rem;
  padding: 0.5rem 0.75rem;
}

.btn-google {
  color: white !important;
  background-color: #ea4335 !important;
}

.btn-facebook {
  color: white !important;
  background-color: #3b5998 !important;
}

.sign-container {
  width: 50%;
}

#emailAuthentication,
#phoneAuthentication,
#floatingInputEmail,
#floatingPhone {
  width: 70%;
  display: inline-block;
}
/* === END memberSignUp.jsp CSS */

/* Nav */
.navbar {
  box-shadow: 1px 1px 10px 0 rgba(0, 0, 0, 0.1);
}
.dropdown-menu a:hover {
  background-color: #fff;
}
#navbarDropdown {
  color: #212529;
}
#navbarSupportedContent a:hover {
  color: #b3e3c3;
}
/* search */
.input-group {
  position: relative;
  display: flex;
  flex-wrap: wrap;
  align-items: stretch;
  width: 100%;
}
/* button */
.btn-primary {
  color: #212529;
  border-color: #212529;
  background-color: #fff;
}
.btn-primary:hover,
.btn-primary:focus,
.btn-primary:active,
.btn-primary.active,
.open > .dropdown-toggle.btn-primary {
  color: #fff;
  background-color: #b3e3c3;
  border: 1px solid #b3e3c3;
}
</style>
