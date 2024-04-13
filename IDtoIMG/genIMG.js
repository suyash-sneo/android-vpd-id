function generateImageFromId(idString) {
    var canvas = document.getElementById('myCanvas');
    var ctx = canvas.getContext('2d');

    // Set background color
    Math.seedrandom(idString); // Seed the random number generator with the ID
    ctx.fillStyle = getRandomColor();
    ctx.fillRect(0, 0, canvas.width, canvas.height);

    // Draw shapes
    for (var i = 0; i < 8; i++) {
        var shapeType = i % 6; // Randomly choose shape type
        var x = getRandomInt(0, canvas.width);
        var y = getRandomInt(0, canvas.height);
        var size = getRandomInt(50, 200); // Random size between 10 and 100
        var width = getRandomInt(50, 200); // Random width between 10 and 100
        var height = getRandomInt(50, 200); // Random height between 10 and 100

        ctx.fillStyle = getRandomColor();
        switch (shapeType) {
            case 0:
                // Square
                ctx.fillRect(x, y, size, size);
                break;
            case 1:
                // Circle
                ctx.beginPath();
                ctx.arc(x + size / 2, y + size / 2, size / 2, 0, 2 * Math.PI);
                ctx.fill();
                break;
            case 2:
                // Triangle
                ctx.beginPath();
                ctx.moveTo(x, y);
                ctx.lineTo(x + size, y);
                ctx.lineTo(x + size / 2, y + size);
                ctx.closePath();
                ctx.fill();
                break;
            case 3:
                // Trapezoid
                ctx.beginPath();
                ctx.moveTo(x, y);
                ctx.lineTo(x + width / 4, y + height);
                ctx.lineTo(x + width - width / 4, y + height);
                ctx.lineTo(x + width, y);
                ctx.closePath();
                ctx.fill();
                break;
            case 4:
                // Rhombus
                ctx.beginPath();
                ctx.moveTo(x + width / 2, y);
                ctx.lineTo(x + width, y + height / 2);
                ctx.lineTo(x + width / 2, y + height);
                ctx.lineTo(x, y + height / 2);
                ctx.closePath();
                ctx.fill();
                break;
            case 5:
                // Ellipse
                ctx.beginPath();
                ctx.ellipse(x + width / 2, y + height / 2, width / 2, height / 2, 0, 0, 2 * Math.PI);
                ctx.fill();
                break;
        }
    }
}

function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

// Example usage with provided string
generateImageFromId("AE8Hr/tnoH1wVpdX202dNy8Hipm0dEYTMgzPfA==");
