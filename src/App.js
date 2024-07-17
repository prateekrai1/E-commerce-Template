import react from 'react'
import './App.css';
import Navbar from './main/Navbar/Navbar';
import Analytics from './main/Analytics/Analytics';
import Users from './main/Users/Users';
import { BrowserRouter as Router, Switch, Route, BrowserRouter, Routes } from 'react-router-dom';
import Products from './main/Products/Products';


const navbarData = [
  {id:0, title: 'Analytics'},
  {id:1, title:'Users'},
  {id:2,title: 'Categories'},
  {id:3, title:'Products'},
  {id:4, title:'Orders'},
  {id:5, title:'Reviews'},
]

function App() {
  return (
    <div className='flex flex-row gap-6'>
      <Navbar navbardata ={navbarData}/>
      <BrowserRouter>
      <Routes>
        <Route path="/" element={<Analytics />} />
        <Route path="/users" element={<Users/>} />
        <Route path="/products" element={<Products/>} />
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
