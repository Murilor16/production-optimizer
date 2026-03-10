import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json'
  }
});

export default {
  // Raw Materials
  getRawMaterials() {
    return api.get('/raw-materials');
  },
  getRawMaterial(id) {
    return api.get(`/raw-materials/${id}`);
  },
  createRawMaterial(data) {
    return api.post('/raw-materials', data);
  },
  updateRawMaterial(id, data) {
    return api.put(`/raw-materials/${id}`, data);
  },
  deleteRawMaterial(id) {
    return api.delete(`/raw-materials/${id}`);
  },

  // Products
  getProducts() {
    return api.get('/products');
  },
  getProduct(id) {
    return api.get(`/products/${id}`);
  },
  createProduct(data) {
    return api.post('/products', data);
  },
  updateProduct(id, data) {
    return api.put(`/products/${id}`, data);
  },
  deleteProduct(id) {
    return api.delete(`/products/${id}`);
  },

  // Optimization
  calculateOptimization() {
    return api.get('/optimization/calculate');
  }
};
