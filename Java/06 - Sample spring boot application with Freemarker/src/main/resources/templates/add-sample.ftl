<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <<#-- we can directly access files inside static folder -->
    <link rel="stylesheet" href="/bootstrap.css">

    <title>Add Sample</title>
</head>
<body>

<div class="container">
    <h1>Add Sample</h1>

    <form action="/add-new-sample" method="post">
        <div class="form-group">
            <label for="id">Id</label>
            <input type="text" class="form-control" id="id" name="id" placeholder="Enter id">
        </div>
        <div class="form-group">
            <label for="data">Data</label>
            <input type="text" class="form-control" id="data" name="data" placeholder="Enter data">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

<#-- we can directly access files inside static folder -->
<script src="/jquery.js"></script>
<script src="/popper.js"></script>
<script src="/bootstrap.js"></script>

</body>
</html>