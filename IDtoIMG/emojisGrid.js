// ID to generate image
let id = "AE8Hr/tnoH1wVpdX202dNy8Hipm0dEYTMgzPfB==";

// Seed the random number generator with the ID
Math.seedrandom(id);

async function fetchEmojis()
{
    // Fetch emojis using try/catch
    try {
        const response = await fetch('https://api.github.com/emojis');
        const data = await response.json();
        const emojis = Object.values(data);
        //const numEmojis = emojis.length;
        //console.log(`Number of emojis: ${numEmojis}`);
        // return fetched and parsed data as an array of values
        return emojis;
    }
    catch (error) {
        console.error('Error fetching emojis:', error);
        return [];
    }
}

async function generateImage()
{
    let canvas = document.getElementById('image');
    let image = canvas.getContext('2d');

    // Fetch emojis
    const emojis = await fetchEmojis();

    // Size of Grid: X by X
    let gridSize = 2;

    // # of pixels between each emoji
    let padding = 10;

    // Set emoji width and height
    let emojiWidth = (canvas.width - padding * (gridSize + 1)) / gridSize;
    let emojiHeight = (canvas.height - padding * (gridSize + 1)) / gridSize;
    
    // Add emoji to each element in grid
    for (let row = 0; row < gridSize; row++)
    {
        for (let col = 0; col < gridSize; col++)
        {
            // Randomly choose an emoji using random()
            let emoji = emojis[Math.floor(Math.random() * emojis.length)];

            // Create an image element
            let img = new Image();
            img.src = emoji;

            // Draws emoji image at (x,y) with specificed width and height
            let x = padding + col * (emojiWidth + padding);
            let y = padding + row * (emojiHeight + padding);
            img.onload = function() {
                image.drawImage(img, x, y, emojiWidth, emojiHeight);
            };
        }
    }

    // Create gradient background
    let gradient = image.createLinearGradient(0, 0, canvas.width, canvas.height);
    gradient.addColorStop(0, "#c0c0c0");
    gradient.addColorStop(1, "#f0f0f0");

    // Set gradient as background
    image.fillStyle = gradient;
    image.fillRect(0, 0, canvas.width, canvas.height);
}

// Call generateImage() after content is loaded
document.addEventListener('DOMContentLoaded', generateImage());