// ID to generate image
let id = "AE8Hr/tnoH1wVpdX202dNy8Hipm0dEYTMgzPfA==";

// Seed the random number generator with the ID
Math.seedrandom(id);

function generateImageFromId()
{
    let canvas = document.getElementById('image');
    let image = canvas.getContext('2d');

    // Create gradient background
    let gradient = image.createLinearGradient(0, 0, canvas.width, canvas.height);
    gradient.addColorStop(0, "#c0c0c0");
    gradient.addColorStop(1, "#f0f0f0");

    // Set gradient as background
    image.fillStyle = gradient;
    image.fillRect(0, 0, canvas.width, canvas.height);

    // Draw shapes
    for (let i = 0; i < 6; i++)
    {
        let x = getRandomInt(0, canvas.width);
        let y = getRandomInt(0, canvas.height);
        let size = getRandomInt(100, 300); // Random size between 10 and 100
        let width = getRandomInt(100, 300); // Random width between 10 and 100
        let height = getRandomInt(100, 300); // Random height between 10 and 100

        // Ensure shape stays within canvas bounds
        x = Math.min(x, canvas.width - size);
        y = Math.min(y, canvas.height - size);

        image.fillStyle = getRandomColor();
        switch (Math.floor(Math.random() * 8))
        {
            case 0:
                // Square
                image.fillRect(x, y, size, size);
                break;
            case 1:
                // Circle
                image.beginPath();
                image.arc(x + size / 2, y + size / 2, size / 2, 0, 2 * Math.PI);
                image.fill();
                break;
            case 2:
                // Triangle
                image.beginPath();
                image.moveTo(x, y);
                image.lineTo(x + size, y);
                image.lineTo(x + size / 2, y + size);
                image.closePath();
                image.fill();
                break;
            case 3:
                // Trapezoid
                image.beginPath();
                image.moveTo(x, y);
                image.lineTo(x + width / 4, y + height);
                image.lineTo(x + width - width / 4, y + height);
                image.lineTo(x + width, y);
                image.closePath();
                image.fill();
                break;
            case 4:
                // Rhombus
                image.beginPath();
                image.moveTo(x + width / 2, y);
                image.lineTo(x + width, y + height / 2);
                image.lineTo(x + width / 2, y + height);
                image.lineTo(x, y + height / 2);
                image.closePath();
                image.fill();
                break;
            case 5:
                // Ellipse
                image.beginPath();
                image.ellipse(x + width / 2, y + height / 2, width / 2, height / 2, 0, 0, 2 * Math.PI);
                image.fill();
                break;
            case 6:
                // Octagon
                image.beginPath();
                let octagonSize = Math.min(size, width, height);
                image.moveTo(x + octagonSize * 0.3, y);
                image.lineTo(x + octagonSize * 0.7, y);
                image.lineTo(x + octagonSize, y + octagonSize * 0.3);
                image.lineTo(x + octagonSize, y + octagonSize * 0.7);
                image.lineTo(x + octagonSize * 0.7, y + octagonSize);
                image.lineTo(x + octagonSize * 0.3, y + octagonSize);
                image.lineTo(x, y + octagonSize * 0.7);
                image.lineTo(x, y + octagonSize * 0.3);
                image.closePath();
                image.fill();
                break;
            case 7:
                // Hexagon
                image.beginPath();
                let hexagonSize = Math.min(size, width, height);
                image.moveTo(x + hexagonSize * 0.25, y);
                image.lineTo(x + hexagonSize * 0.75, y);
                image.lineTo(x + hexagonSize, y + hexagonSize * 0.5);
                image.lineTo(x + hexagonSize * 0.75, y + hexagonSize);
                image.lineTo(x + hexagonSize * 0.25, y + hexagonSize);
                image.lineTo(x, y + hexagonSize * 0.5);
                image.closePath();
                image.fill();
                break;
        }
    }
}

function getRandomInt(min, max)
{
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

function getRandomColor()
{
    // Array of basic colors
    const basicColors = ["brown", "pink", "gray", "darkgray", "black", "red", "green", "blue", "yellow", "magenta", "cyan", "orange", "purple"];
    // Randomly choose a color from the array
    return basicColors[Math.floor(Math.random() * basicColors.length)];
}

// Call function
generateImageFromId();