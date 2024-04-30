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

    // Generate 25 shapes in a 5x5 grid with padding
    let gridSize = 3;
    let padding = 20;
    let cellWidth = (canvas.width - padding * (gridSize + 1)) / gridSize;
    let cellHeight = (canvas.height - padding * (gridSize + 1)) / gridSize;
    for (let row = 0; row < gridSize; row++) {
        for (let col = 0; col < gridSize; col++) {
            let x = padding + col * (cellWidth + padding);
            let y = padding + row * (cellHeight + padding);

            // Randomly choose shape type
            let shapeType = Math.floor(Math.random() * 9);

            // Draw shape
            image.fillStyle = getRandomColor();
            switch (shapeType)
            {
                case 0:
                    // Square
                    let size = Math.min(cellWidth, cellHeight);
                    image.fillRect(x + (cellWidth - size) / 2, y + (cellHeight - size) / 2, size, size);
                    break;
                case 1:
                    // Circle
                    let radius = Math.min(cellWidth, cellHeight) / 2;
                    image.beginPath();
                    image.arc(x + cellWidth / 2, y + cellHeight / 2, radius, 0, 2 * Math.PI);
                    image.fill();
                    break;
                case 2:
                    // Rectangle
                    image.fillRect(x + cellWidth * 0.1, y + cellHeight * 0.1, cellWidth * 0.8, cellHeight * 0.6);
                    break;
                case 3:
                    // Octagon
                    image.beginPath();
                    let octagonSize = Math.min(cellWidth, cellHeight);
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
                case 4:
                    // Hexagon
                    image.beginPath();
                    image.moveTo(x + cellWidth * 0.25, y);
                    image.lineTo(x + cellWidth * 0.75, y);
                    image.lineTo(x + cellWidth, y + cellHeight * 0.5);
                    image.lineTo(x + cellWidth * 0.75, y + cellHeight);
                    image.lineTo(x + cellWidth * 0.25, y + cellHeight);
                    image.lineTo(x, y + cellHeight * 0.5);
                    image.closePath();
                    image.fill();
                    break;
                case 5:
                    // Oval
                    let ovalWidth = cellWidth * 0.9;
                    let ovalHeight = cellHeight * 0.6;
                    image.beginPath();
                    image.ellipse(x + cellWidth / 2, y + cellHeight / 2, ovalWidth / 2, ovalHeight / 2, 0, 0, 2 * Math.PI);
                    image.fill();
                    break;
                case 6:
                    // Heart
                    let heartHeight = cellHeight * 0.9;
                    image.beginPath();
                    image.moveTo(x + cellWidth / 2, y + heartHeight / 5);
                    image.bezierCurveTo(
                        x + cellWidth / 2, y,
                        x + cellWidth * 0.1, y,
                        x + cellWidth * 0.1, y + heartHeight / 5
                    );
                    image.bezierCurveTo(
                        x + cellWidth * 0.1, y + heartHeight * 2 / 3,
                        x + cellWidth / 2, y + heartHeight * 5 / 6,
                        x + cellWidth / 2, y + heartHeight
                    );
                    image.bezierCurveTo(
                        x + cellWidth / 2, y + heartHeight * 5 / 6,
                        x + cellWidth * 9 / 10, y + heartHeight * 2 / 3,
                        x + cellWidth * 9 / 10, y + heartHeight / 5
                    );
                    image.bezierCurveTo(
                        x + cellWidth * 9 / 10, y,
                        x + cellWidth / 2, y,
                        x + cellWidth / 2, y + heartHeight / 5
                    );
                    image.closePath();
                    image.fill();
                    break;
                case 7:
                    // Cross
                    let crossWidth = cellWidth * 0.8;
                    let crossHeight = cellHeight * 0.4;
                    image.fillRect(x + (cellWidth - crossWidth) / 2, y + (cellHeight - crossHeight) / 2, crossWidth, crossHeight);
                    image.fillRect(x + (cellWidth - crossHeight) / 2, y + (cellHeight - crossWidth) / 2, crossHeight, crossWidth);
                    break;
                case 8:
                    // Triangle
                    image.beginPath();
                    image.moveTo(x, y);
                    image.lineTo(x + cellWidth, y);
                    image.lineTo(x + cellHeight / 2, y + cellHeight);
                    image.closePath();
                    image.fill();
                    break;
            }
        }
    }
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