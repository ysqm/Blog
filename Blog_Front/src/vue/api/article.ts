import { request } from '@/request';
import store from "../store/modules";

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
    formData.append('userId', store.state.uid);
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