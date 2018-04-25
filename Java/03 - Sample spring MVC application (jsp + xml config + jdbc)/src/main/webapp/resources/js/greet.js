var greet = function (elementId, message) {
    var limit = message.length;
    var count = 0;
    var currentMessage = '';

    var generate = function () {
        if (count < limit) {
            currentMessage += message[count];
            count ++;
        } else {
            currentMessage = '';
            count = 0;
        }
    };
    
    var append = function () {
        document.getElementById(elementId).innerHTML = currentMessage;
    };

    setInterval(function () {
        generate();
        append();
    }, 100);
};