import React from 'react';
import ConfigurationWindow from './components/configurationwindow/configurationwindow'
import './App.css';

function App() {
  return (
    <div className="App">
        <div className="outside-configuration-window">
          <ConfigurationWindow/>
        </div>
    </div>
  );
}

export default App;
