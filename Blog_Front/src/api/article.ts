import { request } from '@/request';
import store from "../store/modules";
import axios from "axios";
const API_BASE_URL = '/api/articles';

interface ArticleData {
    code: number;
    msg: string;
    data: any;
}

interface CreateArticleDTO {
    userId: number;
    title: string;
    file: File;
    status: string;
    tagIds: number[];
}

export function createArticle(articleDTO: CreateArticleDTO) {
    const formData = new FormData();
    formData.append('userId', articleDTO.userId.toString());
    formData.append('title', articleDTO.title);
    formData.append('status', articleDTO.status);
    formData.append('file', articleDTO.file);
    formData.append('tagIds', JSON.stringify(articleDTO.tagIds));

    return request<ArticleData>({
        url: '/api/articles/create',
        method: 'post',
        data: formData,
    });


}
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
export const getLatestArticles = () => {
    return request({
        url: '/api/articles/latest',
        method: 'get',
    });
}
