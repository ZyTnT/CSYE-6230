const express = require('express');
const app = express();

app.get('/', (req, res) => {
  const name = req.query.name;
  const message = `Welcome ${name}`;
  res.send(message);
});

app.listen(3000, () => {
  console.log('Server is listening on port 3000');
});
