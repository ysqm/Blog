import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/articles';

export const createArticle = (userId, title, status, file) => {
    const formData = new FormData();
    formData.append('userId', userId);
    formData.append('title', title);
    formData.append('status', status);
    formData.append('file', file);

    return axios.post(`${API_BASE_URL}/create`, formData, {
        headers: {
            'Content-Type': 'multipart/form-data',
        },
    });
};

export const updateArticle = (articleId, title, status, file) => {
    const formData = new FormData();
    formData.append('title', title);
    formData.append('status', status);
    formData.append('file', file);

    return axios.put(`${API_BASE_URL}/update/${articleId}`, formData, {
        headers: {
            'Content-Type': 'multipart/form-data',
        },
    });
};

export const deleteArticle = (articleId) => {
    return axios.delete(`${API_BASE_URL}/delete/${articleId}`);
};

export const getArticleById = (articleId) => {
    return axios.get(`${API_BASE_URL}/${articleId}`);
};

export const getArticlesByUserId = (userId) => {
    return axios.get(`${API_BASE_URL}/user/${userId}`);
};
