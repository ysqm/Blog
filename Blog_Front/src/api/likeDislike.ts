import { request } from '@/request';
import store from "../store/modules";

interface LikeDislikeDTO {
    userId: number;
    commentId: number;
    articleId: number;
    recordStatus: string;
}

interface UserData {
    code: number;
    msg: string;
    data: any;
}

export function addCommentLike(userId: number,commentId: number) {
    const recordStatus = "like"
    const token = localStorage.getItem('token');
    return request<UserData>({
        url: '/api/likeDislike/add',
        method: "post",
        headers: {
            Authorization: `Bearer ${token}`
        },
        data: {
            userId,
            commentId,
            recordStatus,

        }
    });
}
export function addCommentDislike(userId: number,commentId: number) {
    const recordStatus = "dislike"
    const token = localStorage.getItem('token');
    return request<UserData>({
        url: '/api/likeDislike/add',
        method: "post",
        headers: {
            Authorization: `Bearer ${token}`
        },
        data: {
            userId,
            commentId,
            recordStatus
        }
    });
}
export function addArticleLike(userId: number,targetId: number) {
    const recordType = "article"
    const recordStatus = "like"
    const token = localStorage.getItem('token');
    return request<UserData>({
        url: '/api/likeDislike/add',
        method: "post",
        headers: {
            Authorization: `Bearer ${token}`
        },
        data: {
            userId,
            recordType,
            recordStatus,
            targetId
        }
    });
}
export function addArticleDislike(userId: number,targetId: number) {
    const recordType = "article"
    const recordStatus = "dislike"
    const token = localStorage.getItem('token');
    return request<UserData>({
        url: '/api/likeDislike/add',
        method: "post",
        headers: {
            Authorization: `Bearer ${token}`
        },
        data: {
            userId,
            targetId,
            recordType,
            recordStatus
        }
    });
}
export function getCommentLikeNum(commentId: number) {
    const token = localStorage.getItem('token');
    return request<UserData>({
        url: `/api/likeDislike/count/comment/${commentId}`,
        method: "get",
        headers: {
            Authorization: `Bearer ${token}`
        },

    });
}
export function getCommentDislikeNum(commentId: number) {
    const token = localStorage.getItem('token');
    return request<UserData>({
        url: `/api/likeDislike/count/comment/${commentId}`,
        method: "get",
        headers: {
            Authorization: `Bearer ${token}`
        },

    });
}
export function getArticleLikeNum(articleId: number) {
    const token = localStorage.getItem('token');
    return request<UserData>({
        url: `/api/likeDislike/count/article/${articleId}`,
        method: "get",
        headers: {
            Authorization: `Bearer ${token}`
        },

    });
}
export function removeArticleDislike(userId: number,targetId: number) {
    const recordType = "article"
    const recordStatus = "dislike"
    const token = localStorage.getItem('token');
    return request<UserData>({
        url: '/api/likeDislike/remove',
        method: "post",
        headers: {
            Authorization: `Bearer ${token}`
        },
        data: {
            userId,
            targetId,
            recordType,
            recordStatus
        }
    });
}
export function removeCommentDislike(userId: number,targetId: number) {
    const recordType = "comment"
    const recordStatus = "dislike"
    const token = localStorage.getItem('token');
    return request<UserData>({
        url: '/api/likeDislike/remove',
        method: "post",
        headers: {
            Authorization: `Bearer ${token}`
        },
        data: {
            userId,
            targetId,
            recordType,
            recordStatus
        }
    });
}
export function removeArticleLike(userId: number,targetId: number) {
    const recordType = "article"
    const recordStatus = "like"
    const token = localStorage.getItem('token');
    return request<UserData>({
        url: '/api/likeDislike/remove',
        method: "post",
        headers: {
            Authorization: `Bearer ${token}`
        },
        data: {
            userId,
            targetId,
            recordType,
            recordStatus
        }
    });
}
export function removeCommentLike(userId: number,targetId: number) {
    const recordType = "comment"
    const recordStatus = "like"
    const token = localStorage.getItem('token');
    return request<UserData>({
        url: '/api/likeDislike/remove',
        method: "post",
        headers: {
            Authorization: `Bearer ${token}`
        },
        data: {
            userId,
            targetId,
            recordType,
            recordStatus
        }
    });
}


