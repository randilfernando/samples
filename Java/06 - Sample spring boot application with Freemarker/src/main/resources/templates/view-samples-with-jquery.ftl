<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <<#-- we can directly access files inside static folder -->
    <link rel="stylesheet" href="/bootstrap.css">

    <title>Load data with JQuery</title>
</head>
<body>

<div class="container">
    <h1>Load data with JQuery</h1>
    <form>
        <div class="form-group">
            <label for="id">Id</label>
            <input type="text" class="form-control" id="id" name="id" placeholder="Enter id">
        </div>
        <button id="getSampleButton" type="button" class="btn btn-primary" onclick="viewSample()">View</button>
    </form>

    <div class="card">
        <div id="viewSampleArea" class="card-body">
        </div>
    </div>
</div>

<#-- we can directly access files inside static folder -->
<script src="/jquery.js"></script>
<script src="/popper.js"></script>
<script src="/bootstrap.js"></script>
<script>
    var viewSample = function () {
        var id = $('#id').val();
        // pass callback function call after data received
        sendGetSampleRequest(id, function (sample) {
            // execute logic after callback function executes
            if (sample) {
                $('#viewSampleArea').html("Id: " + sample.id + ", Data: " + sample.data)
            } else {
                $('#viewSampleArea').html("404: Not found")
            }
        });
    };

    // get callback function (cb)
    // since java script is asynchronous we need to use callback to send data back
    var sendGetSampleRequest = function (id, cb) {
        $.get("/api/samples/" + id, function (sample) {
            cb(sample)
        })
    };
</script>

</body>
</html>