import { request } from '@/request';

interface TagData {
    code: number;
    msg: string;
    data: any;
}

export function getAllTags() {
    return request<TagData>({
        url: '/api/tag/all',
        method: 'get',
    });
}

export function createTag(tagName: string) {
    return request<TagData>({
        url: '/api/tag/create',
        method: 'post',
        data: { tagName },
    });
}

export function approveTag(tagId: number) {
    return request<TagData>({
        url: `/api/tag/approve/${tagId}`,
        method: 'post',
    });
}
