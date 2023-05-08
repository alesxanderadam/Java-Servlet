<%--
  Created by IntelliJ IDEA.
  User: Quang Huy
  Date: 5/5/2023
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<% String contextPath = request.getContextPath();  %>

<html>
<head>
    <title>Product management</title>

  <!-- Bootstrap CSS v5.2.1 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

    <script>
        function deleteItem(item) {
            var xhr = new XMLHttpRequest();
            xhr.open("DELETE", "${contextPath}/products");
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.send("item=" + item);
        }
    </script>
</head>
<body>
    <div class="container">
      <h1 class="text-center">Product Management</h1>
      <div class="mt-2">
        <form action="<%=contextPath%>/products" method="post">
            <div class="form-group">
              <p>Product Name</p>
              <input class="form-control" name="productName" />
              <p class="text text-danger d-none"></p>
            </div>
            <div class="form-group">
              <p>Quatity</p>
              <input class="form-control" name="Quatity" />
              <p class="text text-danger d-none" id="err_tenSinhVien_required"></p>
            </div>
            <div class="form-group">
              <p>Price</p>
              <input class="form-control" name="Price" />
              <p class="text text-danger d-none"></p>
            </div>

            <div class="form-group mt-2">
              <button class="btn btn-success mt-2" type="submit">Submit</button>
            </div>
        </form>

        <div class="mt-5">
          <table class="table ">
            <thead class="thead-light">
            <th scope="col">#</th>
            <th scope="col">Product Name</th>
            <th scope="col">Quatity</th>
            <th scope="col"> Price</th>
            <th scope="col">Action</th>
            </thead>
            <tbody>
            <c:forEach var="product" items="${productList}" varStatus="loop">>
              <tr>
                <td>${loop.index + 1}</td>
                <td>${product.getProductName()}</td>
                <td>${product.getQuatity()}</td>
                <td>${product.getPrice()}</td>
                <td><button class="btn btn-dark" onclick="deleteItem('${item}')">Delete</button></td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>



<!-- Bootstrap JavaScript Libraries -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous">
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
        integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz" crossorigin="anonymous">
</script>
</body>
</html>
