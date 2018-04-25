<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <<#-- we can directly access files inside static folder -->
    <link rel="stylesheet" href="/bootstrap.css">

    <title>View All Samples</title>
</head>
<body>

<div class="container">
    <h1>View All Samples</h1>

    <ul class="list-group">
    <#-- iterate through samples passed via Model -->
            <#list samples as sample>
                <li class="list-group-item">Id: ${sample.id}, Data: ${sample.data}</li>
            </#list>
    </ul>
</div>

<#-- we can directly access files inside static folder -->
<script src="/jquery.js"></script>
<script src="/popper.js"></script>
<script src="/bootstrap.js"></script>

</body>
</html>