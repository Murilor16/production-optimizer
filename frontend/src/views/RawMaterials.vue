<template>
  <div class="raw-materials">
    <h1>Matérias-Primas</h1>
    
    <div class="form-section">
      <h2>{{ editingId ? 'Editar' : 'Adicionar' }} Matéria-Prima</h2>
      <form @submit.prevent="saveRawMaterial">
        <input v-model="form.code" placeholder="Código" required />
        <input v-model="form.name" placeholder="Nome" required />
        <input v-model.number="form.stockQuantity" type="number" step="0.01" placeholder="Quantidade em Estoque" required />
        <input v-model="form.unit" placeholder="Unidade (ex: kg, unidades)" />
        <div class="form-actions">
          <button type="submit">{{ editingId ? 'Atualizar' : 'Criar' }}</button>
          <button type="button" @click="resetForm" v-if="editingId">Cancelar</button>
        </div>
      </form>
    </div>

    <div class="list-section">
      <h2>Lista de Matérias-Primas</h2>
      <table>
        <thead>
          <tr>
            <th>Código</th>
            <th>Nome</th>
            <th>Estoque</th>
            <th>Unidade</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in rawMaterials" :key="item.id">
            <td>{{ item.code }}</td>
            <td>{{ item.name }}</td>
            <td>{{ item.stockQuantity }}</td>
            <td>{{ item.unit }}</td>
            <td>
              <button @click="editRawMaterial(item)">Editar</button>
              <button @click="deleteRawMaterial(item.id)" class="delete">Excluir</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import api from '../services/api';

export default {
  data() {
    return {
      rawMaterials: [],
      form: {
        code: '',
        name: '',
        stockQuantity: 0,
        unit: ''
      },
      editingId: null
    };
  },
  mounted() {
    this.loadRawMaterials();
  },
  methods: {
    async loadRawMaterials() {
      try {
        const response = await api.getRawMaterials();
        this.rawMaterials = response.data;
      } catch (error) {
        alert('Erro ao carregar matérias-primas');
      }
    },
    async saveRawMaterial() {
      try {
        if (this.editingId) {
          await api.updateRawMaterial(this.editingId, this.form);
          alert('✅ Matéria-prima atualizada com sucesso!');
        } else {
          await api.createRawMaterial(this.form);
          alert('✅ Matéria-prima criada com sucesso!');
        }
        this.resetForm();
        this.loadRawMaterials();
      } catch (error) {
        const message = error.response?.data?.error || 'Erro ao salvar matéria-prima';
        alert('❌ ' + message);
      }
    },
    editRawMaterial(item) {
      this.form = { ...item };
      this.editingId = item.id;
      window.scrollTo({ top: 0, behavior: 'smooth' });
    },
    async deleteRawMaterial(id) {
      if (confirm('⚠️ Tem certeza que deseja excluir esta matéria-prima?\n\nEsta ação não pode ser desfeita.')) {
        try {
          await api.deleteRawMaterial(id);
          alert('✅ Matéria-prima excluída com sucesso!');
          this.loadRawMaterials();
        } catch (error) {
          alert('❌ Erro ao excluir matéria-prima');
        }
      }
    },
    resetForm() {
      this.form = { code: '', name: '', stockQuantity: 0, unit: '' };
      this.editingId = null;
    }
  }
};
</script>

<style scoped>
.raw-materials {
  max-width: 1400px;
  margin: 0 auto;
  padding: 2rem;
}

.raw-materials h1 {
  font-size: 2.5rem;
  font-weight: 700;
  color: white;
  text-align: center;
  margin-bottom: 3rem;
  text-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
}

.form-section, .list-section {
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(10px);
  padding: 2.5rem;
  border-radius: 20px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.1);
  margin-bottom: 2rem;
}

.form-section h2, .list-section h2 {
  color: #2c3e50;
  margin-bottom: 1.5rem;
  font-size: 1.5rem;
}

form {
  display: flex;
  flex-direction: column;
  gap: 1.25rem;
}

input {
  padding: 0.875rem 1.25rem;
  border: 2px solid #e2e8f0;
  border-radius: 12px;
  font-size: 1rem;
  transition: all 0.3s ease;
}

input:focus {
  outline: none;
  border-color: #667eea;
  box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
}

.form-actions {
  display: flex;
  gap: 1rem;
  margin-top: 0.5rem;
}

button {
  padding: 0.875rem 1.75rem;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  border: none;
  border-radius: 12px;
  cursor: pointer;
  font-weight: 600;
  font-size: 1rem;
  transition: all 0.3s ease;
}

button:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 20px rgba(102, 126, 234, 0.4);
}

button.delete {
  background: linear-gradient(135deg, #f56565 0%, #e53e3e 100%);
}

button.delete:hover {
  box-shadow: 0 8px 20px rgba(245, 101, 101, 0.4);
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

td button {
  padding: 0.5rem 1rem;
  font-size: 0.875rem;
  margin-right: 0.5rem;
}
</style>
