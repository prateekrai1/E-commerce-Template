import React, { useEffect, useState } from "react";
import { allusers } from "../../Service";

const Users = () => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);

  async function getAllUsers() {
    try {
      const response = await allusers();
      setUsers(response.data);
    } catch (error) {
      <div>
        <h1>Something went wrong while retrieving Users</h1>
      </div>;
    } finally {
      setLoading(false);
    }
  }

  if (loading) {
    <div>
      <h1>Loading Users...</h1>
    </div>;
  }
  useEffect(() => {
    getAllUsers();
  }, []);

  if (!users || users.length === 0) {
    return <div>No user data available.</div>;
  }

  const keys = Object.keys(users[0]);

  return (
    <div>
      <h1 className="text-xl m-4 p-4">Users</h1>
      <table>
        <thead>
          <tr>
            {keys.map((key, index) => (
              <th
                key={index}
                className="px-4 py-2 border-b border-gray-200 bg-gray-100 text-left text-base font-semibold text-gray-700 uppercase tracking-wider"
              >
                {key}
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {users.map((user, index) => (
            <tr
              key={index}
              className="border-b border-gray-200 hover:bg-gray-100"
            >
              {keys.map((key, index) => (
                <td key={index} className="px-4 py-2 text-md text-gray-700">
                  {user[key]}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Users;
