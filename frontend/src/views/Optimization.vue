<template>
  <div class="optimization">
    <h1>Otimização de Produção</h1>
    
    <div class="info-section">
      <p>Clique no botão abaixo para calcular o plano de produção ótimo baseado nos níveis de estoque atuais.</p>
      <button @click="calculateOptimization" :disabled="loading">
        {{ loading ? 'Calculando...' : 'Calcular Produção Ótima' }}
      </button>
    </div>

    <div v-if="result" class="result-section">
      <h2>Resultados da Otimização</h2>
      <div class="total-revenue">
        <h3>Receita Total: R$ {{ result.totalRevenue.toFixed(2) }}</h3>
      </div>

      <table v-if="result.suggestions.length > 0">
        <thead>
          <tr>
            <th>Código do Produto</th>
            <th>Nome do Produto</th>
            <th>Preço Unitário</th>
            <th>Quantidade a Produzir</th>
            <th>Valor Total</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="suggestion in result.suggestions" :key="suggestion.productId">
            <td>{{ suggestion.productCode }}</td>
            <td>{{ suggestion.productName }}</td>
            <td>R$ {{ suggestion.productPrice.toFixed(2) }}</td>
            <td>{{ suggestion.quantity }}</td>
            <td>R$ {{ suggestion.totalValue.toFixed(2) }}</td>
          </tr>
        </tbody>
      </table>

      <div v-else class="no-results">
        <p>Nenhuma sugestão de produção disponível. Verifique seus níveis de estoque e composições de produtos.</p>
      </div>
    </div>
  </div>
</template>

<script>
import api from '../services/api';

export default {
  data() {
    return {
      result: null,
      loading: false
    };
  },
  methods: {
    async calculateOptimization() {
      this.loading = true;
      try {
        const response = await api.calculateOptimization();
        this.result = response.data;
        if (this.result.suggestions.length > 0) {
          alert('✅ Otimização calculada com sucesso!\n\nReceita Total: R$ ' + this.result.totalRevenue.toFixed(2));
        } else {
          alert('⚠️ Nenhuma sugestão de produção disponível.\n\nVerifique se há estoque suficiente.');
        }
      } catch (error) {
        alert('❌ Erro ao calcular otimização');
      } finally {
        this.loading = false;
      }
    }
  }
};
</script>

<style scoped>
.optimization {
  max-width: 1400px;
  margin: 0 auto;
  padding: 2rem;
}

.optimization h1 {
  font-size: 2.5rem;
  font-weight: 700;
  color: white;
  text-align: center;
  margin-bottom: 3rem;
  text-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
}

.info-section {
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(10px);
  padding: 3rem;
  border-radius: 20px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.1);
  text-align: center;
  margin-bottom: 2rem;
}

.info-section p {
  color: #64748b;
  font-size: 1.1rem;
  margin-bottom: 2rem;
  line-height: 1.6;
}

.info-section button {
  padding: 1.25rem 3rem;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  border: none;
  border-radius: 12px;
  cursor: pointer;
  font-weight: 600;
  font-size: 1.1rem;
  transition: all 0.3s ease;
}

.info-section button:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow: 0 8px 25px rgba(102, 126, 234, 0.4);
}

.info-section button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.result-section {
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(10px);
  padding: 2.5rem;
  border-radius: 20px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.1);
  animation: slideUp 0.5s ease;
}

@keyframes slideUp {
  from { opacity: 0; transform: translateY(30px); }
  to { opacity: 1; transform: translateY(0); }
}

.result-section h2 {
  color: #2c3e50;
  margin-bottom: 1.5rem;
  font-size: 1.5rem;
}

.total-revenue {
  background: linear-gradient(135deg, #48bb78 0%, #38a169 100%);
  color: white;
  padding: 2rem;
  border-radius: 15px;
  margin-bottom: 2rem;
  text-align: center;
  box-shadow: 0 8px 25px rgba(72, 187, 120, 0.3);
}

.total-revenue h3 {
  margin: 0;
  font-size: 2rem;
  font-weight: 700;
  color: white;
}

table {
  width: 100%;
  border-collapse: separate;
  border-spacing: 0;
}

thead {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

th {
  padding: 1rem;
  text-align: left;
  color: white;
  font-weight: 600;
  text-transform: uppercase;
  font-size: 0.875rem;
  letter-spacing: 0.5px;
}

th:first-child {
  border-top-left-radius: 12px;
}

th:last-child {
  border-top-right-radius: 12px;
}

td {
  padding: 1rem;
  border-bottom: 1px solid #e2e8f0;
  color: #4a5568;
}

tbody tr {
  transition: all 0.2s ease;
}

tbody tr:hover {
  background: #f7fafc;
}

.no-results {
  text-align: center;
  padding: 3rem;
  color: #64748b;
  font-size: 1.1rem;
}
</style>
