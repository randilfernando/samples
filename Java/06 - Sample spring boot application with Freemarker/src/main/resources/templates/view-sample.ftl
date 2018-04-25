<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <<#-- we can directly access files inside static folder -->
    <link rel="stylesheet" href="/bootstrap.css">

    <title>View Sample</title>
</head>
<body>

<div class="container">
    <h1>View Sample</h1>

    <#-- ?? check whether the sample attribute available -->
    <#if sample??>
        <div class="card">
            <div class="card-body">
                Id: ${sample.id}, Data: ${sample.data}
            </div>
        </div>
    <#else>
        <div class="card-body">
            404: Not found sample with Id: ${id}
        </div>
    </#if>
</div>

<#-- we can directly access files inside static folder -->
<script src="/jquery.js"></script>
<script src="/popper.js"></script>
<script src="/bootstrap.js"></script>

</body>
</html>