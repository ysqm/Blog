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
    const formData = new FormData();
    formData.append('userId', store.state.uid);
    return request<UserData>({
        url: '/api/comment/add',
        method: "post",
        data: {
            userId,
            content,
            articleId
        }
    });
}

export function deleteComment(commentId: string) {
    return request<UserData>({
        url: `/api/comment/delete/${commentId}`,
        method: "post"
    });
}

export function getCommentsByArticleId(articleId: string) {
    return request<UserData>({
        url: `/api/comment/article/${articleId}`,
        method: "get",
        data: {

        }
    });
}
