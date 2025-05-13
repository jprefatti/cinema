/* script.js - integração front-end com API REST usando jQuery */
$(document).ready(function() {
  const apiBase = '/api/filmes';

  // Aplica tema salvo
  if (getCookie('theme') === 'dark') {
    ativarTemaEscuro();
  }

  // Alternar tema
  $('#toggle-tema').click(function() {
    $('body').toggleClass('bg-dark text-white');
    $('table').toggleClass('table-dark');
    const tema = $('body').hasClass('bg-dark') ? 'dark' : 'light';
    document.cookie = 'theme=' + tema + '; path=/';
  });

  function ativarTemaEscuro() {
    $('body').addClass('bg-dark text-white');
    $('table').addClass('table-dark');
  }

  function getCookie(name) {
    const v = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    return v ? v[2] : null;
  }

  function carregarFilmes() {
    $.getJSON(apiBase, function(filmes) {
      const tbody = $('#filmes-tbody').empty();
      filmes.forEach(f => {
        tbody.append(`
          <tr data-id="${f.id}">
            <td>${f.titulo}</td>
            <td>${f.genero}</td>
            <td>${f.anoLancamento}</td>
            <td>
              <button class="btn-editar btn btn-sm btn-primary">Editar</button>
              <button class="btn-deletar btn btn-sm btn-danger">Excluir</button>
            </td>
          </tr>
        `);
      });
    });
  }

  $('#form-cadastrar-filme').submit(function(e) {
    e.preventDefault();
    const dados = {
      titulo: $('#titulo').val(),
      sinopse: $('#sinopse').val(),
      genero: $('#genero').val(),
      anoLancamento: parseInt($('#anoLancamento').val(), 10)
    };
    $.ajax({
      url: apiBase,
      type: 'POST',
      contentType: 'application/json',
      data: JSON.stringify(dados)
    }).done(() => {
      $('#form-cadastrar-filme')[0].reset();
      carregarFilmes();
    });
  });

  $('#filmes-tbody').on('click', '.btn-deletar', function() {
    if (!confirm('Confirma exclusão do filme?')) return;
    const id = $(this).closest('tr').data('id');
    $.ajax({ url: `${apiBase}/${id}`, type: 'DELETE' })
      .done(carregarFilmes);
  });

  $('#filmes-tbody').on('click', '.btn-editar', function() {
    const id = $(this).closest('tr').data('id');
    $.getJSON(`${apiBase}/${id}`, function(f) {
      $('#edit-id').val(f.id);
      $('#edit-titulo').val(f.titulo);
      $('#edit-sinopse').val(f.sinopse);
      $('#edit-genero').val(f.genero);
      $('#edit-anoLancamento').val(f.anoLancamento);
      $('#modal-editar').modal('show');
    });
  });

  $('#form-editar-filme').submit(function(e) {
    e.preventDefault();
    const id = parseInt($('#edit-id').val(), 10);
    const dados = {
      titulo: $('#edit-titulo').val(),
      sinopse: $('#edit-sinopse').val(),
      genero: $('#edit-genero').val(),
      anoLancamento: parseInt($('#edit-anoLancamento').val(), 10)
    };
    $.ajax({
      url: `${apiBase}/${id}`,
      type: 'PUT',
      contentType: 'application/json',
      data: JSON.stringify(dados)
    }).done(() => {
      $('#modal-editar').modal('hide');
      carregarFilmes();
    });
  });

  if ($('#detalhes-filme').length) {
    const filmeId = $('#detalhes-filme').data('id');
    const analiseApi = `/api/filmes/${filmeId}/analises`;

    function carregarAnalises() {
      $.getJSON(analiseApi, function(list) {
        const container = $('#analises-container').empty();
        list.forEach(a => {
          container.append(`
            <div data-id="${a.id}" class="analise-item mb-3">
              <p>${a.analise} <strong>(${a.nota})</strong></p>
              <button class="btn-editar-analise btn btn-sm btn-primary">Editar</button>
              <button class="btn-deletar-analise btn btn-sm btn-danger">Excluir</button>
            </div>
          `);
        });
      });
    }

    $('#form-nova-analise').submit(function(e) {
      e.preventDefault();
      const dados = {
        analise: $('#analise-text').val(),
        nota: parseInt($('#analise-nota').val(), 10)
      };
      $.ajax({ url: analiseApi, type: 'POST', contentType: 'application/json', data: JSON.stringify(dados) })
        .done(() => {
          $('#form-nova-analise')[0].reset();
          carregarAnalises();
        });
    });

    $('#analises-container').on('click', '.btn-deletar-analise', function() {
      if (!confirm('Confirma exclusão da análise?')) return;
      const id = $(this).closest('.analise-item').data('id');
      $.ajax({ url: `${analiseApi}/${id}`, type: 'DELETE' })
        .done(carregarAnalises);
    });

    carregarAnalises();
  }

  carregarFilmes();
});