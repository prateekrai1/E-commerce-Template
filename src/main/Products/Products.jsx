import React, { useEffect, useState } from "react";
import { allProducts } from "../../Service";

const Products = () => {
  const [products, setProducts] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  async function fetchAllProducts() {
    try {
      const response = await allProducts();
      setProducts(response.data);
    } catch(error) {
      <>
        <h1>Something went wrong</h1>
      </>;
    } finally {
      setIsLoading(false);
    }
  }

  if(isLoading){
    <div>
        <h1>Loading...</h1>
    </div>
  }

  useEffect(() => {
    fetchAllProducts();
  },[])

  if (!products || products.length === 0) {
    return <div>No user data available.</div>;
  }
  const keys = Object.keys(products[0])

  return(
   <div>
    <h1 className="text-xl m-4 p-4">Products</h1>
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
          {products.map((products, index) => (
            <tr
              key={index}
              className="border-b border-gray-200 hover:bg-gray-100"
            >
              {keys.map((key, index) => (
                <td key={index} className="px-4 py-2 text-md text-gray-700">
                  {products[key]}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
  </div>
  );
};

export default Products;
