import React from 'react';
import logo from './logo.svg';
import './App.css';

import { GreetServiceClient } from "./proto/greet_grpc_web_pb";
import { Greeting, GreetRequest, GreetResponse} from "./proto/greet_pb";

const greeting = new Greeting();
greeting.setFirstName('John');
greeting.setLastName('Doe');
const request = new GreetRequest();
request.setGreeting(greeting);

const client = new GreetServiceClient('http://localhost:8080');
client.greet(request, {}, function (err: any, response : GreetResponse) {
  console.log(response.getResult());
});

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
