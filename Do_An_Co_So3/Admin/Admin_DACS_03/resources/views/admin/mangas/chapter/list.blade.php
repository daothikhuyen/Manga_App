@extends("admin.main")

@section('contents')
<table class="table">
    <thead>
      <tr>
        <th scope="col" class="text-center">STT</th>
        <th scope="col " class="text-center">Tên tập</th>
        <th scope="col" class="text-center"><th style="width:100px">&nbsp;</th></th>
      </tr>
    </thead>
    <tbody>
        @foreach ($chapters as $key  => $chapter )
            <tr>
                <th scope="row" class="align-middle text-center">{{$key+=1}}</th>
                <td class="align-middle text-center">
                    {{$chapter->title}}
                </td>
                <td class="text-right">
                    <a class="btn btn-primary btn-sm" href="/admin/mangas/chapters/edit/{{ $chapter->id }}">
                        <i class="fa-solid fa-pen-to-square"></i>
                    </a>
                    <a href="#" class="btn btn-danger btn-sm"
                        onclick = "removeRow({{ $chapter->id }},'/admin/mangas/chapters/destroy')">
                        <i class="fa-solid fa-trash-can "></i>
                    </a>
                </td>
        </tr>
        @endforeach
    </tbody>
  </table>
@endsection
