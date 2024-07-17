import React from 'react'

const Navbar = ({navbardata}) => {
  return (
    <div className="h-screen w-64 bg-slate-950 text-white">
      <div className="p-4 text-2xl font-bold text-center bg-red-600">AV Admin</div>
      <nav>
        <ul>
          {navbardata.map((item) => (
            <li key={item.id} className="p-4 hover:bg-gray-700 text-xl" >
              {item.title}
            </li>
          ))}
        </ul>
      </nav>
    </div>
  )
}

export default Navbar