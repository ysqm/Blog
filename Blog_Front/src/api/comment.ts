import { request } from '@/request';
import store from "../store/modules";

interface CommentDTO {
    userId: number;
    commentId: number;
    content: string;
    articleId: number;
}

interface UserData {
    code: number;
    msg: string;
    data: any;
}

export function addComment(userId: number, content: string, articleId: number) {

    const token = localStorage.getItem('token');
    return request<UserData>({
        url: '/api/comment/add',
        method: "post",
        headers: {
            Authorization: `Bearer ${token}`
        },
        data: {
            userId,
            content,
            articleId
        }
    });
}

export function deleteComment(commentId: string) {
    const token = localStorage.getItem('token');
    return request<UserData>({
        url: `/api/comment/delete/${commentId}`,
        method: "post",
        headers: {
            Authorization: `Bearer ${token}`
        },
    });
}

export function getCommentsByArticleId(articleId: number) {
    const token = localStorage.getItem('token');
    return request<UserData>({
        url: `/api/comment/article/${articleId}`,
        method: "get",
        headers: {
            Authorization: `Bearer ${token}`
        }

    });
}
