<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>The Look - Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        body {
            background-image: url('/home-image.jpg');
            background-size: cover;
            background-position: center;
        }
        .navbar-brand, .nav-link {
            font-weight: bold;
        }
        .form-card {
            margin: 2rem auto;
            padding: 2rem;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,.05);
        }
        .form-control, .btn-primary {
            border-radius: 0.5rem;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">The Look</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/contact">Contact</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/about">About Us</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Login</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row col-md-8 offset-md-2">
        <div class="card form-card">
            <div class="card-header">
                <h2 class="text-center">Registration</h2>
            </div>
            <div th:if="${param.success}">
                <div class="alert alert-info">
                    You have successfully registered our app!
                </div>
            </div>
            <div class="card-body">
                <form method="post" role="form" th:action="@{/register/save}" th:object="${customer}">
                    <div class="form-group mb-3">
                        <label class="form-label">Username</label>
                        <input class="form-control" id="username" name="username" placeholder="Enter username" th:field="*{username}" type="text"/>
                        <p th:errors="*{username}" class="text-danger" th:if="${#fields.hasErrors('username')}"></p>
                    </div>

                    <div class="form-group mb-3">
                        <label class="form-label">Email</label>
                        <input class="form-control" id="email" name="email" placeholder="Enter email address" th:field="*{email}" type="text"/>
                        <p th:errors="*{email}" class="text-danger" th:if="${#fields.hasErrors('email')}"></p>
                    </div>

                    <div class="form-group mb-3">
                        <label class="form-label">Password</label>
                        <input class="form-control" id="password" name="password" placeholder="Enter password" th:field="*{password}" type="password"/>
                        <p th:errors="*{password}" class="text-danger" th:if="${#fields.hasErrors('password')}"></p>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-primary" type="submit">Register</button>
                        <span>Already registered? <a th:href="@{/login}">Login here</a></span>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
